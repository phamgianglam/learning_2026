import type { ItemDetail } from "../types/Item";

import config from "../config";

const base_url = config.apiUrl;

function rederInStock(inStock: number) {
    if (inStock > 0) {
        return <p className="text-green-500">In stock</p>
    } else {
        return <p className="text-red-500">Out of stock</p>
    }
}
export default function Item({ item }: { item: ItemDetail }) {

    const imagePath = `${base_url}/images/${item.imagePath}`
    
    return (
        <div>
            <img src={imagePath} alt={item.imageDescription} className="w-full aspect-square" />
            <h2>{item.name}</h2>
            <p className="text-blue-600 font-bold">{item.cost} {item.unit}</p>
            {rederInStock(item.inStock)}
        </div>
    )
}