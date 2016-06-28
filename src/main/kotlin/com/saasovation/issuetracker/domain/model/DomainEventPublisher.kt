package com.saasovation.issuetracker.domain.model

import java.util.*

//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

class DomainEventPublisher private constructor() {

    private var isPublishing: Boolean = false

    @SuppressWarnings("rawtypes")
    private var subscribers: MutableList<Any>? = null

    fun <T:DomainEvent> publish(aDomainEvent: T) {
        if (!this.isPublishing && this.hasSubscribers()) {

            try {
                this.isPublishing = true

                val eventType = aDomainEvent.javaClass

                @SuppressWarnings("unchecked")
                val allSubscribers = this.subscribers()

                for (subscriber  in allSubscribers!!) {
                    val typedSubscriber: DomainEventSubscriber<T> = subscriber as DomainEventSubscriber<T>
                    val subscribedToType = typedSubscriber.subscribedToEventType()

                    if (eventType == subscribedToType || subscribedToType == DomainEvent::class.java) {
                        typedSubscriber.handleEvent(aDomainEvent)
                    }
                }

            } finally {
                this.isPublishing = false
            }
        }
    }

    fun publishAll(aDomainEvents: Collection<DomainEvent>) {
        for (domainEvent in aDomainEvents) {
            this.publish<DomainEvent>(domainEvent)
        }
    }

    fun reset() {
        if (!this.isPublishing) {
            this.setSubscribers(null)
        }
    }

    @SuppressWarnings("unchecked")
    fun <T> subscribe(aSubscriber: DomainEventSubscriber<T>) {
        if (!this.isPublishing) {
            this.ensureSubscribersList()

            this.subscribers()!!.add(aSubscriber)
        }
    }

    init {

        this.isPublishing = false
        this.ensureSubscribersList()
    }

    @SuppressWarnings("rawtypes")
    private fun ensureSubscribersList() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(ArrayList())
        }
    }

    private fun hasSubscribers(): Boolean {
        return this.subscribers() != null
    }

    @SuppressWarnings("rawtypes")
    private fun subscribers(): MutableList<Any>? {
        return this.subscribers
    }

    @SuppressWarnings("rawtypes")
    private fun setSubscribers(aSubscriberList: MutableList<Any>?) {
        this.subscribers = aSubscriberList
    }

    companion object {

        private val instance = object : ThreadLocal<DomainEventPublisher>() {
            override fun initialValue(): DomainEventPublisher {
                return DomainEventPublisher()
            }
        }

        fun instance(): DomainEventPublisher {
            return instance.get()
        }
    }
}
