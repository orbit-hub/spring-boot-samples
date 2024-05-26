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

@Configuration
@EnableStateMachine
public class Config52
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1, action())
				.state(States.S1, action(), null)
				.state(States.S2, null, action())
				.state(States.S2, action())
				.state(States.S3, action(), action());
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