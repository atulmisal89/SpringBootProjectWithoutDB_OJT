package com.jbk.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.jbk.dao.SupplierDao;
import com.jbk.model.Address;
import com.jbk.model.Supplier;

@Repository
public class SupplierDaoImpl implements SupplierDao 
{

	List<Supplier> list = new ArrayList<>();

	public SupplierDaoImpl() 
	{
		list.add(new Supplier(1l, "Yash Distributor", "9011223344",
				new Address(1l, "pune", "pune", "Maharashtra", "411052")));
		
		list.add(new Supplier(2l, "Akash Distributor", "9011223355",
				new Address(1l, "pune", "pune", "Maharashtra", "411052")));
		
		list.add(new Supplier(3l, "Ram Distributor", "9011223356",
				new Address(1l, "pune", "pune", "Maharashtra", "411052")));

	}

	private boolean isExists(Supplier supplier) 
	{

		boolean isExists = false;
		for (Supplier dbSupplier : list) 
		{
			if (supplier.getSupplierName().equalsIgnoreCase(dbSupplier.getSupplierName())
					|| supplier.getSupplierMobileNumber().equals(dbSupplier.getSupplierMobileNumber())) 
			{
				isExists = true;
				break;
			}

		}
		return isExists;  //false
	}

	@Override
	public int addSupplier(Supplier supplier) 
	{
		if (isExists(supplier) == false) 
		{
			list.add(supplier);
			return 1;
		}
		else 
		{
			return 2;
		}

	}

	@Override
	public Supplier getSupplierById(long supplierId) 
	{

		for (Supplier dbSupplier : list) 
		{
			if (dbSupplier.getSupplierId() == supplierId) 
			{
				return dbSupplier;
			}
		}
		return null;

	}

	@Override
	public List<Supplier> getAllSupplier() 
	{

		return list;
	}

	@Override
	public Object deleteSupplier(long supplierId) 
	{
		int status = 0;
		
		if (!list.isEmpty())  //if list is not empty
		{
			for (Supplier dbSupplier : list) 
			{
				if (dbSupplier.getSupplierId() == supplierId) 
				{
					list.remove(dbSupplier);  //if suuplierId is found remove that element
					return list;     //return list
				}
				else 
				{
					status = 1;  //if suplierId is not found
				}
			}
		}
		else 
		{
			status = 2;  //if list is empty
		}

		return status;
	}

	@Override
	public Supplier updateSupplier(Supplier supplier) 
	{

		for (Supplier dbSupplier : list) 
		{

			if (dbSupplier.getSupplierId() == supplier.getSupplierId()) 
			{
				list.set(list.indexOf(dbSupplier), supplier);  //If a match is found, it uses list.indexOf(dbSupplier) to find the index of the dbSupplier object in the list
			                                                   //It then uses list.set() to update the Supplier at that index with the provided supplier object. 
				return supplier;                              
				
			}
		}


		return null;
	}

}
