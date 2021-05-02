pid=$(ps ax | grep frontend| grep -v ' grep ' | awk '{print $1}')
if [ "$pid" == "" ]
then
    echo "down frontend"
else
    kill -9 ${pid}
    echo "${pid}"
    echo "kill frontend"
fi

pid=$(ps ax | grep gradlew| grep -v ' grep ' | awk '{print $1}')
if [ "$pid" == "" ]
then
    echo "down All serve"
else
    kill -9 ${pid}
    echo "${pid}"
    echo "kill All serve"
fi
