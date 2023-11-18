FROM node:10-alpine
EXPOSE 3000
WORKDIR /app
COPY . .
RUN npm ci
CMD ["npm", "run", "start"]
