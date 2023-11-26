const icon = document.querySelector('.icon');
const search = document.querySelector('.search');
    
icon.onclick = function() {
    search.classList.toggle('active')
}

document.getElementById('searchForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const query = document.getElementById('mysearch').value;
    if (query) {
        const searchURL = `https://www.google.com/search?q=${query}`;
        window.location.href = searchURL;
    }
});