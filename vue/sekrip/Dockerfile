FROM nginx:stable-alpine

COPY /dist/ /usr/share/nginx/html/

COPY sekrip/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80