package ro.sda.java37.finalProject.services;

public interface Mapper <E,D>{
    D convertToDto(E entity);
    E convertToEntity(D dto);
}