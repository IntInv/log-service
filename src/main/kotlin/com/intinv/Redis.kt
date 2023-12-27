package com.intinv;

import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPubSub
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.channels.Channel
import kotlin.concurrent.thread

class Redis(
	val channel: Channel<String>,
	val hostname: String = "localhost",
	val port: Int = 6379
) {

	val pool = JedisPool(hostname, port)
	val jedisPubSub = object : JedisPubSub() {
		override fun onPMessage(pattern: String?, channel: String?, message: String?) {
			val msg = message ?: "<error>"
			val ch = channel ?: "<unknown channel>"
			runBlocking {
				this@Redis.channel.send(ch + ": " + msg)
			}
		}
	}

	public fun run() {
		thread {
			pool.resource.psubscribe(jedisPubSub, "intinv.api.*")
		}
	}
}
