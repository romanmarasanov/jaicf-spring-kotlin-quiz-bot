package com.justai.jaicf.spring.connections

import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.spring.config.BotConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class JaicpPoller(
    private val botApi: BotApi,
    private val botConfiguration: BotConfig
): ApplicationRunner, CoroutineScope {
    override val coroutineContext = Dispatchers.Default

    override fun run(args: ApplicationArguments?) {
            JaicpPollingConnector(
                botApi = botApi,
                accessToken = botConfiguration.accessToken,
                channels = listOf(TelegramChannel)
            ).runBlocking()
    }
}