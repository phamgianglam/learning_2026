import { useParams } from 'react-router';

interface ItemDetail {
    id: number;
    name: string;
    description: string;
    cost: number;
    unit: string;
    imagePath: string;
    imageDescription: string;
    inStock: number;
    discount: boolean;
    discountPrice: number;
}

let itemHolder: ItemDetail = {
    id: 1,
    name: "stubItem",
    description: "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quibusdam laborum adipisci dolores qui porro excepturi, cupiditate earum voluptatum sit ea unde consequatur exercitationem. Nulla laudantium fuga, at ratione deleniti animi.",
    cost: 250,
    unit: "$",
    imagePath: "/case-01.jpg",
    imageDescription: "a item in sale",
    inStock: 20,
    discount: true,
    discountPrice: 15
}

function renderPrice() {
    if (itemHolder.discount) {
        return <div>
            <h2 className="line-through" id="price">Price: <span>{itemHolder.cost} {itemHolder.unit}</span></h2>
            <h2 className="text-red-500 font-bold" id="discountPrice">Discount Price: <span>{itemHolder.discountPrice} {itemHolder.unit}</span></h2>
        </div>
    }
    return <h2 id="price">Price: <span>{itemHolder.cost} {itemHolder.unit}</span></h2>
}

export default function ItemDetailedPage() {
    return <div>
        <h1 id="name" className="font-bold text-3xl">{itemHolder.name}</h1>
        <br/>

        <div id="itemPanel" className="flex justify-evenly">
            <div id="itemImagePanel" className="flex-1">
                <img src={itemHolder.imagePath} alt={itemHolder.imageDescription} className="size-150 bg-amber-600" />
            </div>
            <div id="itemPricingAndBuyingPanel" className="flex flex-col justify-between w-50 h-40">
                <div id="pricingPanel" className="rounded-xl p-0.5">
                    {renderPrice()}
                </div>
                <div id="buyingPanel" className="flex h-10 gap-1">
                    <div className="flex-1 bg-blue-500 rounded-xl">
                        <a className="flex items-center justify-center h-full w-full cursor-pointer text-white">Add to Cart</a>
                    </div>
                    <div className="flex-1 bg-red-700 rounded-xl">
                        <a className="flex items-center justify-center h-full w-full cursor-pointer text-white">Buy Now</a>
                    </div>
                </div>
            </div>
        </div>

        <br />
        <div id="itemDescriptionPanel" className="flex flex-col">
            <h2 id="descriptionTitle" className="font-bold text-2xl">Description</h2>
            <p id="description" className="text-lg">{itemHolder.description}</p>
        </div>
    </div>;
}
