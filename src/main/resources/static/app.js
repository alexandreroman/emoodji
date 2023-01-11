function reloadEmoodji() {
    console.log("Synchronizing emoodji");
    axios.post("/api/v1/emoodji").then(res => {
        document.getElementById("emoodji").innerText = res.data;
        clearSelection();
    });
};

function getEmoodji() {
    console.log("Loading emoodji");
    axios.get("/api/v1/emoodji").then(res => {
        document.getElementById("emoodji").innerText = res.data;
        clearSelection();
    });
};

function clearSelection() {
    if (window.getSelection) {
        if (window.getSelection().empty) {  // Chrome
            window.getSelection().empty();
        } else if (window.getSelection().removeAllRanges) {  // Firefox
            window.getSelection().removeAllRanges();
        }
    } else if (document.selection) {  // IE?
        document.selection.empty();
    }
}
