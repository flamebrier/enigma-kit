#!/bin/bash

sleep 25

#until curl enigma_db:5432; do
#  >&2 echo "PostgreSQL is unavailable - sleeping"
#  sleep 5
#done

java -jar enigma.jar
