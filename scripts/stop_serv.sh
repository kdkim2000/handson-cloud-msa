echo $1

if [ "$1" == "" ]
then
    echo "USEAGE: stop_serv.sh '"'serve_name'"'"
    exit
fi


pid=$(ps ax | grep $1 | grep -v ' grep ' | awk '{print $1}')

echo "${pid}"

if [ "$pid" == "" ]
then
    echo "$1 is down"
else
    kill -9 ${pid}
    echo "kill $1"
fi