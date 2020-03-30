[![Build status](https://dev.azure.com/uni-proj81GE16s5gd/foodtrack/_apis/build/status/fdtrck%20-%20CI)](https://dev.azure.com/uni-proj81GE16s5gd/foodtrack/_build/latest?definitionId=1)

# FoodTrack

This is very basic template of REST API returning five records of forecast under /api/weatherforecast, 
and React frontend printing those records. 

## How to run this? 

For now you can run backend and frontend separately for development purposes, or using `docker-compose`  with
production build of frontend served by Nginx.

### In development environment:

1. Clone this repo
`git clone <repo-link>`

* Install frontend dependencies
```
cd frontend/
yarn install
yarn start
```
Frontend should start on http://localhost

* Build backend OUTDATED DON'T DO IT :) 
```
cd backend/
dotnet publish -c Release -o out
dotnet run
```

Backend should start on http://localhost:5000

### In production environment:

* Make sure if you have Docker installed on your machine. If not, download it from https://www.docker.com
and configure for your OS

* Build frontend image: 
```
cd frontend/
docker build -t frontend .
```

* Build backend image: 
```
cd backend/
docker build -t backend .
```

* Make all things **run** by typing `docker-compose up` from main project directory `foodtrack`

### Creating `docker-compose` for development in progress
