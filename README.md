# FoodTrack
## To run this:

1. Clone this repo
`git clone <repo-link>`

2. Install frontend dependencies
```
cd frontend/
yarn install
yarn start
```
Frontend should start on 'http://localhost:3000'

3. Build backend
```
cd backend/
dotnet publish -c Release -o out
dotnet run
```

4. In another terminal run backend
```
dotnet bin/Debug/netcoreapp3.1/backend.dll
```

Backend should start on 'http://localhost:5000'

### Creating docker-compose in progress

