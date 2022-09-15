// For displaying entries
let displayEntries = document.getElementById("displayEntries");
let loadButton = document.getElementById("loadEntriesButton");

// For adding entries
let addButton = document.getElementById("addEntryButton")
let classInput = document.getElementById("classNameInput");
let mapInput = document.getElementById("mapNameInput");
let moneyInput = document.getElementById("moneyInput");
let expInput = document.getElementById("expInput");
let urlInput = document.getElementById("urlInput");

// Event listeners for buttons
loadButton.addEventListener("click", apiGetEntries);
addButton.addEventListener("click", apiPostEntries);

async function apiGetEntries() {
    console.log("loadButton clicked");
    let response = await fetch("http://localhost:9000/entries");
    response = await response.json();
    loadEntries(response);
}

async function apiGetEntryById(id) {
    let response = await fetch("http://localhost:9000/entries/" + id);
    response = await response.json();
    console.log(response);
    return {classID:response.classID, mapID:response.mapID, moneyEarned:response.moneyEarned, expEarned:response.expEarned, url:response.url}
}

async function loadEntries(response) {
    displayEntries.innerHTML = "";
    
    console.log(response);

    // let entryList = document.createElement("ul");

    // for(let i = 0; i < response.length; i++) {
    //     console.log(entry);
    //     console.log(entry.classID);
    // }

}
