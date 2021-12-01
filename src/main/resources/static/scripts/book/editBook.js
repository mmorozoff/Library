var oldName;
var oldAuthor;
var oldYear;
var oldPublisher;
var oldGenre;
var oldCount;

function onLoad() {
    oldName = document.getElementById("bookName").value;
    oldAuthor = document.getElementById("bookAuthor").value;
    oldYear = document.getElementById("bookYear").value;
    oldPublisher = document.getElementById("bookPublisher").value;
    oldGenre = document.getElementById("bookGenre").value;
    oldCount = document.getElementById("bookCount").value;
}

function showMessage() {
    var newName = document.getElementById("bookName").value;
    var newAuthor = document.getElementById("bookAuthor").value;
    var newYear = document.getElementById("bookYear").value;
    var newPublisher = document.getElementById("bookPublisher").value;
    var newGenre = document.getElementById("bookGenre").value;
    var newCount = document.getElementById("bookCount").value;
}