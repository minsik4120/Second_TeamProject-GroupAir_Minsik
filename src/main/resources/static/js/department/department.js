document.addEventListener("DOMContentLoaded", function() {
    // Get the current URL
    var currentURL = window.location.href;

    // Get the link elements
    var links = document.querySelectorAll('a#lowDe');

    // Loop through each link element
    links.forEach(function(link) {
        // Get the value of the href attribute
        var hrefValue = link.getAttribute('href');

        // Check if the href attribute value matches the current URL
        if (hrefValue === currentURL) {
            // Add a class to the anchor element
            link.classList.add('highlighted-link');
        }
    });
});