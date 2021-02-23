FROM postgres:13.0
#COPY docker/postgres/enigma.dump /tmp/enigma.dump
COPY docker/postgres/enigma.sql /docker-entrypoint-initdb.d/enigma.sql
#COPY docker/postgres/init.sh /docker-entrypoint-initdb.d/init.sh
#CMD psql enigma < tmp/enigma.dump
#COPY docker/postgres/init.sql /docker-entrypoint-initdb.d/init.sql