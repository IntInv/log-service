package com.intinv

import kotlinx.coroutines.channels.Channel
import org.slf4j.LoggerFactory

suspend fun main() {
	println("Starting logging service")

	val logger = LoggerFactory.getLogger("Log Service")
	val channel = Channel<String>()
	val redis = Redis(channel)

	redis.run()
	
	while(true) {
		val message = channel.receive()
		logger.debug(message)
	}
}
