#!/bin/bash
sleep 10
bin/dary -Ddb.default.url="postgres://postgres:${POSTGRES_PASSWORD}@db/postgres" -DapplyEvolutions.default=${DB_EVOLUTION}
