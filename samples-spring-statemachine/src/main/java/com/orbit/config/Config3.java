package com.orbit.config;

import com.orbit.event.Events;
import com.orbit.state.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;


//支持三种不同类型的转换：
// external， internal和local
// 转换由信号触发 （这是发送到状态机的事件）或定时器。
@Configuration
@EnableStateMachine
public class Config3
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1, action())//为初始状态/初态S1定义了一个动作。
				.state(States.S1, action(), null)//为状态S1定义了一个进入操作，并将退出操作留空。
				.state(States.S2, null, action())//为状态S2定义了一个退出操作，并将进入操作留空。
				.state(States.S2, action())//为状态S2定义了单个状态操作。
				.state(States.S3, action(), action())//为状态S3定义了进入和退出操作。
				.states(EnumSet.allOf(States.class));

		//S1的initial()和state() 只要想在初始状态进入或退出时干些什么裁定以
		//initial()里的action是在启动的时候才触发，只运行一次的初始化操作
		//state() 是进入这个状态退出这个状态才执行
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		transitions
			.withExternal()//外部转换器
				.source(States.S1).target(States.S2)
				.event(Events.E1)
				.guard(guard())
				.action(action())
				.and()
			.withInternal()
				.source(States.S2)
				.event(Events.E2)
				.and()
			.withLocal()
				.source(States.S2).target(States.S3)
				.event(Events.E3)
				.guardExpression("true");//SPeL表达式
	}

	//在guard方法可以访问StateContext的情况下进行评估,保护状态转换
	public Guard<States, Events> guard() {
		return new Guard<States, Events>() {

			@Override
			public boolean evaluate(StateContext<States, Events> context) {
				return true;
			}
		};
	}

	@Bean
	public Action<States, Events> action() {
		return new Action<States, Events>() {

			@Override
			public void execute(StateContext<States, Events> context) {
				// do something
			}
		};
	}
}