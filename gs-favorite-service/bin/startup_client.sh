#!/bin/bash

CLASSPATH=conf:gs-favorite-service.jar
for f in lib/*.jar; do
  CLASSPATH=${CLASSPATH}:$f
done

gs_favorite_client_pid=`ps -ef |grep "FavoriteClient" |grep -v grep |grep java |awk '{print $2}'`

start_run () {
    java -DAPP_NAME=gsFavoriteClient -cp $CLASSPATH com.ctrip.gs.favorite.service.FavoriteClient >> /var/log/gs_favorite/gs_favorite_startup_client.log 2>&1 &
}

case "$1" in
start)
        start_run
        ;;
stop)
        if [[ $gs_favorite_client_pid == "" ]]; then
            echo "gs favorite client was stoped."
          else
            kill -9 $gs_favorite_client_pid
        fi
        ;;
restart)
        kill -9 $gs_favorite_client_pid
        start_run
        ;;
status)
        if [[ $gs_favorite_client_pid -gt 1 ]]; then
            echo "gs favorite client  is running."
          else
            echo "gs favorite client  is stop."
        fi
        ;;
  *)
        echo $"Usage: $0 {start|stop|restart}"
esac