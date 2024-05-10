package com.orbit.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MessagingRabbitmqApplication {

	static final String topicExchangeName = "spring-boot-exchange";

	static final String queueName = "spring-boot";

	static final String queueAl = "spring-al";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	Queue queueAl() {
		return new Queue(queueAl, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
		return BindingBuilder.bind(queue).to(exchange).with("foo.al.#");
	}
	@Bean
	Binding bindingAl(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter,MessageListenerAdapter listenerAdapterAl) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
		container.addQueueNames(queueName,queueAl);

//		container.setQueueNames(queueAl);
		container.setMessageListener(listenerAdapterAl);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		MessageListenerAdapter receiveMessage = new MessageListenerAdapter(receiver, "receiveMessage");
		Map<String, String> map = new HashMap<>();
		map.put(queueName,"receiveMessage");
		map.put(queueAl,"receiveMessageAl");

		receiveMessage.setQueueOrTagToMethodName(map);
		return receiveMessage;
	}

	@Bean
	MessageListenerAdapter listenerAdapterAl(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessageAl");
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MessagingRabbitmqApplication.class, args).close();
	}

}
