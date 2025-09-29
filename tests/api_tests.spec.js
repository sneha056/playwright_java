import { test, expect } from '@playwright/test';
import exp from 'constants';




test('API GET REQUEST', async ({ request }) => {

    const response = await request.get('https://reqres.in/api/users/2')//to call api

    expect(response.status()).toBe(200);// to verify status code

    const text = await response.text();// to get response body

    console.log(text); //to print in playwright terminal(console)
    console.log(await response.json()); //to print in playwright terminal(console) in json format

    expect(text).toContain('Janet');// to verify that the text is present in response body

})

test('API POST REQUEST', async ({ request }) => {
    const response = await request.post('https://reqres.in/api/users', {//to call api
        headers: {
            'x-api-key': 'reqres-free-v1'
        },
        data: {
            name: 'Sneha',
            job: 'QA'
        }
    })
    console.log(await response.json());
    expect(response.status()).toBe(201);// to verify status code

})

test('API PUT REQUEST', async ({ request }) => {
    const response = await request.put('https://reqres.in/api/users/2', {
        headers: {
            'x-api-key': 'reqres-free-v1'
        },
        data: {
            name: 'Sneha',
            job: 'QA'
        }
    });
    console.log(response.status(), await response.json());
    expect(response.status()).toBe(200);
});

test('API DELETE REQUEST', async ({ request }) => {
    const response = await request.delete('https://reqres.in/api/users/2', {
        headers: {
            'x-api-key': 'reqres-free-v1'
        }
    });
    //console.log(text);
    expect(response.status()).toBe(204);
});