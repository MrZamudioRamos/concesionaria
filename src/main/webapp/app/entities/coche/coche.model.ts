import { IVenta } from 'app/entities/venta/venta.model';

export interface ICoche {
  id?: number;
  marca?: string | null;
  modelo?: string | null;
  color?: string | null;
  numeroSerie?: string | null;
  precio?: number | null;
  exposicion?: boolean | null;
  coches?: IVenta[] | null;
  venta?: IVenta | null;
}

export class Coche implements ICoche {
  constructor(
    public id?: number,
    public marca?: string | null,
    public modelo?: string | null,
    public color?: string | null,
    public numeroSerie?: string | null,
    public precio?: number | null,
    public exposicion?: boolean | null,
    public coches?: IVenta[] | null,
    public venta?: IVenta | null
  ) {
    this.exposicion = this.exposicion ?? false;
  }
}

export function getCocheIdentifier(coche: ICoche): number | undefined {
  return coche.id;
}
