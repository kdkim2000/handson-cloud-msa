echo "****************start all serve*******************"

pid=$(ps ax | grep gradlew | grep eureka | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eureka
    ./gradlew bootRun &
    cd ../scripts
    echo "eureka is running"
    sleep 10
fi

pid=$(ps ax | grep gradlew | grep gateway | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../gateway
    ./gradlew bootRun &
    cd ../scripts
    echo "gateway is running"
    sleep 10
fi

pid=$(ps ax | grep gradlew | grep backend | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eshop-backend
    ./gradlew bootRun &
    cd ../scripts
    echo "backend is running"
    sleep 10
fi

pid=$(ps ax | grep gradlew | grep adservice | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eshop-adservice
    ./gradlew bootRun &
    cd ../scripts
    echo "adservice is running"
    sleep 10
fi

pid=$(ps ax | grep gradlew | grep currency | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eshop-currencyservice
    ./gradlew bootRun &
    cd ../scripts
    echo "currencyservice is running"
    sleep 10
fi

pid=$(ps ax | grep gradlew | grep shipping | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eshop-shippingservice
    ./gradlew bootRun &
    cd ../scripts
    echo "shippingservice is running"
    sleep 10
fi

