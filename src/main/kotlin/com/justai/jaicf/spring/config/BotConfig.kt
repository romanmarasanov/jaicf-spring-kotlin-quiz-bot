package com.justai.jaicf.spring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "kotlin.bot")
data class BotConfig (
    val accessToken: String
)