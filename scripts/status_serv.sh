echo $1

pid=$(ps ax | grep $1 | grep -v ' grep ' | awk '{print $1}')

echo "${pid}"

if [ "$pid" == "" ]

then
    echo "$1 is down"
else
    echo "$1 is running"
fi