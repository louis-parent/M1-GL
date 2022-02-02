window.addEventListener("load", () => {
	const agencies = [{
		name: "Voyage Voyage",
		url: "http://localhost:8081/api/v1/"
	}, {
		name: "Erwan Aviation",
		url: "http://localhost:8082/api/v1/"
	}];
	
	const form = document.querySelector("#search");
	
	const offerList = document.querySelector("#offer-list");
	const offerTemplate = document.querySelector(".offer");
	offerTemplate.remove();
	
	form.addEventListener("submit", (event) => {
		event.preventDefault();

		offerList.innerHTML = "";
		let data = new FormData(form);
		
		for(let agency of agencies)
		{
			let url = agency.url + "offers/" + data.get("city") + "/" + data.get("start") + "/" + data.get("end") + "/" + data.get("persons") + "/" + data.get("stars");					
			fetch(url).then(response => {
				return response.json();
			}).then(data => {
				
				for(let offer of data)
				{
					console.log(offer);
					let currentOffer = offerTemplate.cloneNode(true);
					currentOffer.querySelector(".hotel-name").innerText = offer.hotelName;
					currentOffer.querySelector(".hotel-stars").innerText = offer.hotelStars + " étoiles";
					currentOffer.querySelector(".hotel-address").innerText = offer.hotelAddress.number + " " + offer.hotelAddress.street + ", " + offer.hotelAddress.zipCode + " " + offer.hotelAddress.city + ", " + offer.hotelAddress.country;
					currentOffer.querySelector(".hotel-persons").innerText = offer.roomMaxPersons + " personnes";
					currentOffer.querySelector(".agency-name").innerText = agency.name;
					currentOffer.querySelector(".agency-price").innerText = offer.roomPrice + "€";
					currentOffer.querySelector(".hotel-image").src = "data:image/png;base64," + offer.image;

					offerList.appendChild(currentOffer);
				}
			});
		}

		return false;
	});
});
