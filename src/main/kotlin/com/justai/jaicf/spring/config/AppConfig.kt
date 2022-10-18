package com.justai.jaicf.spring.config

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.spring.scenario.MainScenario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig(
    private val botConfiguration: BotConfig
) {

    @Bean
    fun botApi(mainScenario: MainScenario) = BotEngine(
        scenario = mainScenario,
        activators = arrayOf(RegexActivator),
        conversationLoggers = arrayOf(
            JaicpConversationLogger(botConfiguration.accessToken)
        )
    )

}