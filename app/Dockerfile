FROM node:18-alpine

ARG CACHEBUST=1

WORKDIR /app
COPY package.json package.json
RUN npm install
COPY . .
EXPOSE 3000
CMD ["node", "app.js"]

