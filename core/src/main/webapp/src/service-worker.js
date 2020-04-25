const PRECACHE = 'precache-v3';


//SPACE LEFT HERE SO JENKINS CAN REPLACE THE FIRST STRING WITH A CACHE WITH THE
//BUILD NUMBER. DO NOT TOUCH THIS.
const RUNTIME = 'runtime';

const PRECACHE_URLS = [
    'index.html',
    'app.js'
];

self.addEventListener('install', event => {
    event.waitUntil(
            caches.open(PRECACHE)
            .then(cache => cache.addAll(PRECACHE_URLS))
            .then(self.skipWaiting())
            );
});

// The activate handler takes care of cleaning up old caches.
self.addEventListener('activate', event => {
    const currentCaches = [PRECACHE, RUNTIME];
    event.waitUntil(
            caches.keys().then(cacheNames => {
        return cacheNames.filter(cacheName => !currentCaches.includes(cacheName));
    }).then(cachesToDelete => {
        return Promise.all(cachesToDelete.map(cacheToDelete => {
            return caches.delete(cacheToDelete);
        }));
    }).then(() => self.clients.claim())
            );
});

self.addEventListener('fetch', event => {
    console.log(event.request.headers.values())
    if (event.request.url.startsWith(self.location.origin) 
            && event.request.method === "GET"
            &&(event.request.url.match(/.*\.(js|html|css|png|jpeg)/g) !== null)) {
        event.respondWith(
                caches.match(event.request).then(cachedResponse => {
            if (cachedResponse && cachedResponse.ok) {
                return cachedResponse;
            }
            return caches.open(RUNTIME).then(cache => {
                return fetch(event.request).then(response => {
                    return cache.put(event.request, response.clone()).then(() => {
                        return response;
                    });
                });
            });
        })
                );
    }
});