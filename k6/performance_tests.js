import http from 'k6/http';
import { check } from 'k6';
import { SharedArray } from 'k6/data';

const BASE_URL = 'http://pokedex-api:8080';

const pokemonNames = new SharedArray('pokemon names', function () {
  return ['pikachu', 'bulbasaur', 'charizard', 'squirtle'];
});

export default function () {
  pokemonNames.forEach((name) => {
    const url = `${BASE_URL}/pokemon/${name}`;
    const res = http.get(url);

    check(res, {
      'status is 200': (r) => r.status === 200,
      'response time is under 200ms': (r) => r.timings.duration < 200,
    });
  });

  pokemonNames.forEach((name) => {
    const url = `${BASE_URL}/pokemon/translated/${name}`;
    const res = http.get(url);

    check(res, {
      'status is 200': (r) => r.status === 200,
      'response time is under 200ms': (r) => r.timings.duration < 200,
    });
  });
}
