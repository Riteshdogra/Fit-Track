🏋️‍♂️ FitTrack – AI-Powered Personalized Fitness Recommendation System

📌 Overview
FitTrack is an AI-powered Spring Boot microservices-based fitness application that delivers personalized workout and nutrition recommendations based on user performance data.

🚀 Features

✅ User authentication & profile management
✅ Workout tracking (sets, reps, duration, calories, progress)
✅ AI-driven recommendation engine for workouts & diet plans
✅ Personalized nutrition suggestions based on performance
✅ RESTful microservices architecture (scalable & modular)
✅ Postgrsql database integration for persistent storage


🏗️ Tech Stack

Backend Framework: Spring Boot (Microservices)

Database: postgresql,mongodb

Service Discovery: Netflix Eureka

Message Broker: RabbitMQ


📂 Project Structure

fittrack/

│── user-service/             # Handles user registration & profiles  
│── Activity-service/         # Manages workout data & tracking  
│── recommendation-service/   # AI-driven recommendation engine  
│── Ai-service/               # Provides personalized diet plans  
│── api-gateway/              # Routes requests to microservices  
│── config-service/           # Centralized configuration management  
│── Eureka-service/           # Service registry (Eureka/Consul)  

