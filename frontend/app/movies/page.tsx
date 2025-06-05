'use client'
import Navbar from '@/components/Navbar'
import React, { useEffect, useState } from 'react'
import Image from 'next/image'
import { Card } from '@/components/ui/card'
import { useRouter } from 'next/navigation'
import Cookies from 'js-cookie'
import { useParams } from 'next/navigation'

export default function Movies() {
    const [movies, setMovies] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const router = useRouter();
    useEffect(() => {
        const token = Cookies.get('auth-token');
        console.log('Token:', token);
        if (!token) {
            alert('You are not authenticated. Please log in.');
            console.error('No authentication token found. Redirecting to login.');
            router.push('/auth/login');
            return;
        }

        const fetchTopRatedMovies = async () => {
            try {
                const response = await fetch('http://localhost:3333/user/movies', {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`,
                    }
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch movies');
                }
                const data = await response.json();
                setMovies(data);
            } catch (error) {
                console.error('Error fetching movies:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchTopRatedMovies();
    }, []);

    const handleOnClick = (id: Number) => {
        // router.push(`/movies/${id}`);
    }
    return (
        <div className="min-h-screen bg-black text-white">
            <Navbar />
            <h1 className="text-3xl font-bold text-center mt-20">Movies</h1>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                {movies.length > 0 ? (
                    movies.map((movie) => (
                        <div onClick={() => handleOnClick(movie.id)} key={movie?.id} className="rounded-lg p-4 shadow-md border-2 border-transparent transition-all duration-300 hover:border-rose-300 hover:shadow-purple-400 cursor-pointer">

                            {/* <Image
                                src={movie?.image || "https://via.placeholder.com/300x400"}
                                alt={movie?.name || "movie Image"}
                                className="object-cover w-full h-64"
                                width={300}
                                height={400}
                            /> */}
                            <h2 className="text-xl font-semibold mt-2">{movie?.title}</h2>
                            <p>{movie.description}</p>
                            <p>{movie.genre}</p>
                        </div>
                    ))
                ) : (
                    <p className="text-gray-500">No products available for this category.</p>
                )}
            </div>
        </div>
    );
}
