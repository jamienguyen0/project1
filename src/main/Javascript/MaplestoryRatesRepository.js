window.onload = apiGetEntries;

// For displaying entries
let displayEntries = document.getElementById("displayEntries");

// Dropdown menu for classes
let classSelect = document.getElementById("classSelect");
let classOptions = {};     // Dictionary of existing classes in the dropdown menu

// Dropdown menu for maps
let mapSelect = document.getElementById("mapSelect");
let mapOptions = {};    // Dictionary of existing maps in the dropdown menu

// For adding entries
let addButton = document.getElementById("addEntryButton");
let classInput = document.getElementById("classNameInput");
let mapInput = document.getElementById("mapNameInput");
let moneyInput = document.getElementById("moneyInput");
let expInput = document.getElementById("expInput");
let videoInput = document.getElementById("videoLinkInput");

// Event listeners for buttons
// loadButton.addEventListener("click", apiGetEntries);
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
    return {entryID:response.entryID, classID:response.classID, mapID:response.mapID, money:response.money, exp:response.exp, videoLink:response.videoLink};
}

async function apiGetClassById(id) {
    let response = await fetch("http://localhost:9000/classes/" + id);
    response = await response.json();
    console.log(response);
    return {classID:response.classID, className:response.className}; 
}

async function apiGetMapById(id) {
    let response = await fetch("http://localhost:9000/maps/" + id);
    response = await response.json();
    console.log(response);
    return {mapID:response.mapID, mapName:response.mapName};
}

async function loadEntries(response) {
    displayEntries.innerHTML = "";
    
    console.log(response);

    // Create an unordered list to hold entries
    let entryList = document.createElement("ul");

    for(let i = 0; i < response.length; i++) {
        let entry = await apiGetEntryById(response[i].entryID);
        let msclass = await apiGetClassById(response[i].classID);
        let map = await apiGetMapById(response[i].mapID);
        console.log(entry.className);
        console.log(entry.mapName);

        // Create a single entry to put into the entry list
        let entryListing = document.createElement("li");
        let entryID_p = document.createElement("p");
        let classID_p = document.createElement("p");
        let mapID_p = document.createElement("p");
        let money_p = document.createElement("p");
        let exp_p = document.createElement("p");
        let videoLink_p = document.createElement("p");

        entryID_p.innerHTML = "entry id: " + entry.entryID;
        classID_p.innerHTML = "class: " + msclass.className + " (id: " + entry.classID + ")";
        mapID_p.innerHTML = "map: " + map.mapName + " (id: " + entry.mapID + ")";
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
        
        // Add class and map to diciontaries
        if ( !(msclass.classID in classOptions) ) {
            classOptions[msclass.classID] = msclass.className;
            let classSelectOption = document.createElement("option");
            classSelectOption.value = msclass.className;
            classSelectOption.text = msclass.className;
            classSelect.appendChild(classSelectOption);
        }
        
        if ( !(map.mapID in mapOptions) ) {
            mapOptions[map.mapID] = map.mapName;
            let mapSelectOption = document.createElement("option");
            mapSelectOption.value = map.mapName;
            mapSelectOption.text = map.mapName;
            mapSelect.appendChild(mapSelectOption);
        }
    }

    displayEntries.appendChild(entryList);
}

async function apiPostEntry() {
    console.log("Add entry button clicked");

    let inputEntry = {
        className:classInput.value,
        mapName:mapInput.value,
        money:moneyInput.value,
        exp:expInput.value,
        videoLink:videoInput.value
    }

    let response = await fetch("http://localhost:9000/entries", {
        method:'POST',
        mode:'cors',
        headers: {'Content-type':'application/json'},
        body:JSON.stringify(inputEntry)
    });

    apiGetEntries();
}
