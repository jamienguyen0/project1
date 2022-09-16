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
addButton.addEventListener("click", apiPostEntry);

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
    return {entryID:response.entryID,classID:response.classID,mapID:response.mapID,money:response.money,exp:response.exp,videoLink:response.videoLink};
}

async function loadEntries(response) {
    displayEntries.innerHTML = "";
    
    console.log(response);

    // Create an unordered list to hold entries
    let entryList = document.createElement("ul");

    for(let i = 0; i < response.length; i++) {
        let entry = await apiGetEntryById(response[i].entryID);
        console.log(entry);

        // Create a single entry to put into the entry list
        let entryListing = document.createElement("li");
        let entryID_p = document.createElement("p");
        let classID_p = document.createElement("p");
        let mapID_p = document.createElement("p");
        let money_p = document.createElement("p");
        let exp_p = document.createElement("p");
        let videoLink_p = document.createElement("p");

        entryID_p.innerHTML = "entry id: " + entry.entryID;
        classID_p.innerHTML = "class id: " + entry.classID;
        mapID_p.innerHTML = "map id: " + entry.mapID;
        money_p.innerHTML = "mesos earned (in billions): " + entry.money;
        exp_p.innerHTML = "exp earned (in billions): " + entry.exp;
        videoLink_p.innerHTML = "video link: " + entry.videoLink;

        entryListing.appendChild(entryID_p);
        entryListing.appendChild(classID_p);
        entryListing.appendChild(mapID_p);
        entryListing.appendChild(money_p);
        entryListing.appendChild(exp_p);
        entryListing.appendChild(videoLink_p);

        // Append entry to the entry list
        entryList.appendChild(entryListing);
    }

    displayEntries.appendChild(entryList);

}

async function apiPostEntry() {
    console.log("Add entry button clicked");
    let inputEntry = {}
}
