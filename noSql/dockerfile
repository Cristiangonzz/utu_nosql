FROM node:20 as frontend-builder

WORKDIR /app

COPY ./* /app/

RUN npm install && npm run build

# Usa un servidor como `http-server` para servir los archivos estáticos
RUN npm install -g http-server

EXPOSE 4200
# EXPOSE 4200

CMD ["http-server", "dist", "-p", "4200"]