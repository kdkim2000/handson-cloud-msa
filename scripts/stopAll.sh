pid=$(ps ax | grep gradlew| grep -v ' grep ' | awk '{print $1}')

echo "${pid}"

if [ "$pid" == "" ]
then
    echo "down All serve"
else
    kill -9 ${pid}
    echo "kill All serve"
fi