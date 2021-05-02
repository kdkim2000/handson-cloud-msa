echo "****************start all serve*******************"

pid=$(ps ax | grep gradlew | grep backend | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eshop-backend
    ./gradlew bootRun &
    cd ../scripts
    echo "backend is running"
    sleep 10
fi

pid=$(ps ax | grep frontend | grep -v ' grep ' | awk '{print $1}')

if [ "$pid" == "" ]
then
    cd ../eshop-frontend
    npm audit fix
    npm install
    npm run serve &
    cd ../scripts
    echo "frontend is running"
fi
