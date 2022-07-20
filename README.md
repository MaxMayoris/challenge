# Challenge Spring Boot

Rest API project with Spring Boot 2.7.1

## Testing

URL to test API REST

```bash
  http://localhost:8080/
```

## API Reference

The REST API to the example app is described below.

#### Get all posts

```http
  GET /posts
```

#### Get post by id

```http
  GET /posts/id
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of post to fetch |

#### Get posts containing title

```http
  GET /posts/title?title=title
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `title`      | `string` | **Required**. Title of post to fetch |

#### Get page of posts

```http
  GET /posts/paged?page=:page&size=:size
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `page`      | `string` | **Required**. Number of page to fetch |
| `size`      | `string` | **Required**. Size of page to fetch |

#### Get comments of post
```http
  GET /posts/:postId/comments
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`      | `string` | **Required**. Id of post |
