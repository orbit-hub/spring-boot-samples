package com.orbit.config;

import com.orbit.event.Events;
import com.orbit.state.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

//分层状态
@Configuration
@EnableStateMachine
public class Config2
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1)
				.state(States.S1)
				.and()
				.withStates()
					.parent(States.S1)
					.initial(States.S2)
					.state(States.S2);
	}

}