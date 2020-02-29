# Stage 1
FROM node:8 as react-build
WORKDIR /frontend
COPY . ./
RUN yarn
RUN yarn build

# Stage 2 - the production environment
FROM nginx:alpine
COPY default.conf.template /etc/nginx/conf.d/default.conf.template
COPY --from=react-build /frontend/build /usr/share/nginx/html
CMD /bin/bash -c "envsubst '\$PORT' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf" && nginx -g 'daemon off;'
