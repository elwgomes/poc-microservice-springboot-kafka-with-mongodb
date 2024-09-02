# POC - Microservices with Kafka and Spring Boot

## Description

This architecture consists of three microservices:

order-service: Generates an order and publishes a message to the "orders" topic in Kafka.
stock-service: Listens to the "orders" topic, checks the availability of the products specified in the Order, and publishes the result to the "stock" topic.
payment-service: Listens to the "stock" topic and simulates the approval of the order.
Architecture Overview
Workflow
The order-service creates a new Order with OrderStatus.PENDING and publishes it to the "orders" Kafka topic.
The stock-service consumes the Order message, deserializes it, checks product availability, and, if the products are available, publishes the result to the "stock" Kafka topic.
The payment-service consumes the message from the "stock" topic, simulates the payment process, and updates the OrderStatus to PAID.
