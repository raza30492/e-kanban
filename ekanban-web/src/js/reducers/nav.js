import { NAV_ACTIVATE } from "../utils/constants";

const initialState = {
  active: false,
  itemsAdmin:[
    { path: '/dashboard', label: 'Dashboard'},
    { path: '/reports', label: 'Reports'},
    { path: '/user', label: 'User'},
    { path: '/category', label: 'Category'},
    { path: '/subCategory', label: 'Sub Category'},
    { path: '/section', label: 'Section'},
    { path: '/supplier', label: 'Supplier'},
    { path: '/product', label: 'Product'},
    { path: '/help', label: 'Help'}

  ],
  itemsStore:[
     { path: '/dashboard', label: 'Dashboard'},
     { path: '/reports', label: 'Reports'},
     { path: '/inward', label: 'Inward Scan'},
     { path: '/outward', label: 'Outward Scan'},
     { path: '/awaiting', label: 'Awaiting Order'},
     { path: '/barcode', label: 'Generate Barcode'},
     { path: '/stock', label: 'Stock'},
     { path: '/help', label: 'Help'}

  ],
  itemsPurchase:[
     { path: '/dashboard', label: 'Dashboard'},
     { path: '/reports', label: 'Reports'},
     { path: '/tracking', label: 'Tracking'},
     { path: '/followup', label: 'Follow Up'},
     { path: '/help', label: 'Help'}
  ],
  itemsUser:[
     { path: '/dashboard', label: 'Dashboard'},
     { path: '/help', label: 'Help'}
  ]
};

const handlers = {
  [NAV_ACTIVATE]: (_, action) => ({active: action.payload.active})

};

export default function section (state = initialState, action) {
  let handler = handlers[action.type];
  if( !handler ) return state;
  return { ...state, ...handler(state, action) };
}
