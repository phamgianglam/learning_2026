import { Link } from "react-router"

type ItemListHolder = {
    id: number
    name: string
}

const items: ItemListHolder[] = [
    {
        id: 1,
        name: "item no 1"
    },
    {
        id: 2,
        name: "item no 2"
    },
      {
        id: 3,
        name: "item no 3"
    },
    {
        id: 4,
        name: "item no 4"
    }
]
export default function ItemListPage () {
    return (<>
        {items.map((item) => (
            <Link key={item.id} to={`/item/${item.id}`}>
                <div>
                    <p>id= {item.id}</p>
                    <p>name={item.name}</p>
                </div>
            </Link>
        ))}
    </>);
}