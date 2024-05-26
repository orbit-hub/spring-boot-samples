package com.orbit.config;

import com.orbit.event.Events;
import com.orbit.state.States2;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

//同一个分层状态机有多组状态
@Configuration
@EnableStateMachine
public class Config10
		extends EnumStateMachineConfigurerAdapter<States2, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States2, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States2.S1)
				.state(States2.S2)
				.and()
				.withStates()
					.parent(States2.S2)
					.initial(States2.S2I)
					.state(States2.S21)
					.end(States2.S2F)
					.and()
				.withStates()
					.parent(States2.S2)
					.initial(States2.S3I)
					.state(States2.S31)
					.end(States2.S3F);
	}

}