package com.orbit.config;

import com.orbit.event.Events;
import com.orbit.state.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

import java.util.EnumSet;
//@EnableStateMachineFactory用来创建StateMachineFactory
@Configuration
@EnableStateMachine
// 配置类继承  adapters (EnumStateMachineConfigurerAdapter or StateMachineConfigurerAdapter)
public class Config1Enums
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1)
				.end(States.SF)
				.states(EnumSet.allOf(States.class));
	}

}