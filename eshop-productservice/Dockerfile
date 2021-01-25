FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install --only=production
COPY . .

EXPOSE 8092
CMD ["node", "index.js"]