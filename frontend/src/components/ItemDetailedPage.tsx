import { useQuery } from "@tanstack/react-query";
import { itemClient } from "../api/item";
import { useParams } from "react-router";
import type { ItemDetail } from "../types/Item";

export default function ItemDetailedPage() {
    const {id} = useParams<{id: string}> ();

    const {data: item, isLoading, isError, error} = useQuery({
        queryKey: ['items', id],
        queryFn: () => itemClient.getItem(id!),
        enabled: !!id
    })

    if (isLoading) return <div className="p-10 text-center">Loading item details...</div>;
  
    if (isError) return (
        <div className="p-10 text-red-500">
        Error loading item: {error instanceof Error ? error.message : 'Unknown error'}
        </div>
    );

    const renderPrice = (item: ItemDetail) => {
         if (item.discount) {
        return <div>
            <h2 className="line-through" id="price">Price: <span>{item.cost} {item.unit}</span></h2>
            <h2 className="text-red-500 font-bold" id="discountPrice">Discount Price: <span>{item.discountPrice} {item.unit}</span></h2>
        </div>
    }
    return <h2 id="price">Price: <span>{item.cost} {item.unit}</span></h2>
    }

    if (!item) return <div className="p-10 text-center">Item not found.</div>;

    return <div>
        <h1 id="name" className="font-bold text-3xl">{item.name}</h1>
        <br/>

        <div id="itemPanel" className="flex justify-evenly">
            <div id="itemImagePanel" className="flex-1">
                <img src={item.imagePath} alt={item.imageDescription} className="size-150 bg-amber-600" />
            </div>
            <div id="itemPricingAndBuyingPanel" className="flex flex-col justify-between w-50 h-40">
                <div id="pricingPanel" className="rounded-xl p-0.5">
                    {renderPrice(item)}
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
            <p id="description" className="text-lg">{item.description}</p>
        </div>
    </div>;
}
