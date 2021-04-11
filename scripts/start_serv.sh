echo $1

if [ "$1" == "" ]
then
    echo "USEAGE: start_serv.sh '"'serve_name'"'"
    exit
fi

pid=$(ps ax | grep $1 | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    if [ "eureka" == "$1" ]
    then
        cd ../eureka
        ./gradlew bootRun &
        cd ../scripts
        echo "$1 is running"
    elif [ "backend" == "$1" ]
    then
        cd ../eshop-backend
        ./gradlew bootRun &
        cd ../scripts
        echo "$1 is running"
    elif [ "adservice" == "$1" ]
    then
        cd ../eshop-adservice
        ./gradlew bootRun &
        cd ../scripts
        echo "$1 is running"
    elif [ "currency" == "$1" ]
    then
        cd ../eshop-currencyservice
        ./gradlew bootRun &
        cd ../scripts
        echo "$1 is running"   
    elif [ "shipping" == "$1" ]
    then
        cd ../eshop-shippingservice
        ./gradlew bootRun &
        cd ../scripts
        echo "$1 is running"
    elif [ "gateway" == "$1" ]
    then
        cd ../gateway
        ./gradlew bootRun &
        cd ../scripts
        echo "$1 is running"
    elif [ "frontend" == "$1" ]
    then
        cd ../eshop-frontend
        npm audit fix
        npm install
        npm run serve &
        cd ../scripts
        echo "$1 is running"     
    else 
        echo "$1 is not found"
    fi
else
    echo "${pid}"
    echo "$1 is already running"
fi