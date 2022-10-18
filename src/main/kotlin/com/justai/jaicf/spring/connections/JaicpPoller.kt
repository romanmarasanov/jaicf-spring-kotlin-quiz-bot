package com.justai.jaicf.spring.connections

import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.jaicp.channels.ChatApiChannel
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.spring.accessToken
import com.justai.jaicf.spring.templateBot

fun main() {
    JaicpPollingConnector(
        templateBot,
        accessToken,
        channels = listOf(
            ChatApiChannel,
            ChatWidgetChannel,
            TelephonyChannel
        )
    ).runBlocking()
}