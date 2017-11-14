#!/usr/bin/env bash
mvn clean package
docker build -t earendil06/admission-polytech .