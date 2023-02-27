export const createPost = async (jwt, title, content) => {
  console.log(jwt, title, content);
  try {
    const response = await fetch('/boards', {
      method: 'POST',
      headers: {
        Authorization: jwt,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ title, body: content }),
      credentials: 'include',
    });
    if (!response.ok) {
      throw Error(response.status);
    }
    console.log(response);
  } catch (e) {
    throw Error(e);
  }
};
